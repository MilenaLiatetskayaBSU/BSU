#include <random>
#include <ctime>
#include <iostream>
#include <windows.h>
#include <conio.h>
using namespace std;

	int main()
	{
		cout << "Enter array size ";
		int size_; cin >> size_; cout << endl<<"Array: ";
		srand(time(NULL));
		float* mas = new float[size_];
		for (int i = 0; i < size_; i++)
		{
			mas[i] = rand() / static_cast<float>(RAND_MAX) + rand()%20;
			cout << mas[i] << " ";
		}
		cout << endl;
		wchar_t lpszComLine[80];	

		HANDLE hEnableRead;	
		wchar_t lpszEnableRead[] = L"EnableRead";

		STARTUPINFO si;
		PROCESS_INFORMATION pi;
		HANDLE hWritePipe, hReadPipe, hInheritWritePipe, hInheritReadPipe;
		SECURITY_ATTRIBUTES sa;

		hEnableRead = CreateEvent(NULL, FALSE, FALSE, lpszEnableRead);

		sa.nLength = sizeof(SECURITY_ATTRIBUTES);
		sa.lpSecurityDescriptor = NULL;		
		sa.bInheritHandle = FALSE;		



		
		if (!CreatePipe(
			&hReadPipe,
			&hWritePipe,	
			&sa,
			0))		

		{
			_cputs("Create pipe failed.\n");
			_cputs("Press any key to finish.\n");
			_getch();
			return GetLastError();
		}

		if (!DuplicateHandle(
			GetCurrentProcess(), 
			hWritePipe, 
			GetCurrentProcess(), 
			&hInheritWritePipe, 
			0, 
			TRUE, 
			DUPLICATE_SAME_ACCESS))
		{
			_cputs("Duplicate handle failed.\n");
			_cputs("Press any key to finish.\n");
			_getch();
			return GetLastError();
		}

		if (!DuplicateHandle(
			GetCurrentProcess(),
			hReadPipe, 
			GetCurrentProcess(), 
			&hInheritReadPipe, 
			0,
			TRUE, 
			DUPLICATE_SAME_ACCESS))
		{
			_cputs("Duplicate handle failed.\n");
			_cputs("Press any key to finish.\n");
			_getch();
			return GetLastError();
		}
		
		CloseHandle(hWritePipe);
		CloseHandle(hReadPipe);

		ZeroMemory(&si, sizeof(STARTUPINFO));
		si.cb = sizeof(STARTUPINFO);
		wsprintf(lpszComLine, L"Mult.exe %d %d %d",
			(int)hInheritWritePipe, (int)hInheritReadPipe, size_);
		if (!CreateProcess(
			NULL,	
			lpszComLine,	
			NULL,	
			NULL,TRUE,CREATE_NEW_CONSOLE,	NULL,NULL,&si,&pi		
		))
		{
			_cputs("Create process failed.\n");
			_cputs("Press any key to finish.\n");
			_getch();
			return GetLastError();
		}

		for (int j = 0; j < size_; j++)
		{
			DWORD dwBytesWritten;
			if (!WriteFile(
				hInheritWritePipe,
				&mas[j],
				sizeof(mas[j]),
				&dwBytesWritten,
				NULL))
			{
				_cputs("Write to file failed.\n");
				_cputs("Press any key to finish.\n");
				_getch();
				return GetLastError();
			}
		}

		WaitForSingleObject(hEnableRead, INFINITE);

		float nData;
			DWORD dwBytesRead;
			if (!ReadFile(
				hInheritReadPipe,
				&nData,
				sizeof(nData),
				&dwBytesRead,
				NULL))
			{
				_cputs("Read from the pipe failed.\n");
				_cputs("Press any key to finish.\n");
				_getch();
				return GetLastError();
			}
			cout << endl << nData << endl;


		_cputs("The process finished writing to the pipe.\n");
		_cputs("Press any key to exit.\n");
		_getch();

		CloseHandle(pi.hProcess);
		CloseHandle(pi.hThread);

		return 0;
	}
