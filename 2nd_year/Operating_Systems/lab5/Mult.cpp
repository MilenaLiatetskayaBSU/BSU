#include <windows.h>
#include <conio.h>
#include <iostream>

using namespace std;


int main(int argc, char* argv[])
{
	HANDLE hWritePipe, hReadPipe;
	HANDLE hEnableRead;		
	wchar_t lpszEnableRead[] = L"EnableRead";

	hEnableRead = OpenEvent(EVENT_ALL_ACCESS, FALSE, lpszEnableRead);

	hWritePipe = (HANDLE)atoi(argv[1]);
	hReadPipe = (HANDLE)atoi(argv[2]);
	int size_ = atoi(argv[3]);
	_cputs("Press any key to start communication.\n");
	_getch();
	float result = 1;
	cout << endl << "Array: ";
	for (int j = 0; j < size_; j++)
	{
		float nData;
		DWORD dwBytesRead;
		if (!ReadFile(
			hReadPipe,
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
		cout << nData << " ";
		result*= nData;
	}

	SetEvent(hEnableRead);
	
		DWORD dwBytesWritten;
		if (!WriteFile(
			hWritePipe,
			&result,
			sizeof(result),
			&dwBytesWritten,
			NULL))
		{
			_cputs("Write to file failed.\n");
			_cputs("Press any key to finish.\n");
			_getch();
			return GetLastError();
		}
		
	_getch();

	CloseHandle(hWritePipe);
	CloseHandle(hReadPipe);
	CloseHandle(hEnableRead);

	return 0;
}


