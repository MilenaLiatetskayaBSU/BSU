#define _CRT_SECURE_NO_WARNINGS
#include <conio.h>
#include <Windows.h>
#include <iostream>
#include <string>


using namespace std;

char lpszComLine1[500] = "process1.exe", lpszComLine2[500] = "process2.exe";
char lpszHandle1[20], lpszHandle2[20];
wchar_t wComLine[180];
HANDLE hInEvent;
wchar_t lpEventName[] = L"InEventName";
HANDLE hOutEvent[2];

STARTUPINFO si1,si2, si3;
PROCESS_INFORMATION pi1, pi2,pi3;



int main()
{

	HANDLE hInEvent;
	wchar_t lpEventName[] = L"Event1";
	hInEvent = CreateEvent(NULL, FALSE, FALSE, lpEventName);
	if (hInEvent == NULL)
		return GetLastError();

	wchar_t hOutEvent1[] = L"ONE";
	wchar_t hOutEvent2[] = L"TWO";
	hOutEvent[0] = CreateEvent(NULL, TRUE, FALSE, hOutEvent1);
	if (hOutEvent[0] == NULL)
		return GetLastError();
	hOutEvent[1] = CreateEvent(NULL, TRUE, FALSE, hOutEvent2);
	if (hOutEvent[1] == NULL)
		return GetLastError();
	DWORD dwWaitResult;
	HANDLE	hMutex;
	hMutex = CreateMutex(NULL, FALSE, L"DemoMutex");
	if (hMutex == NULL)
	{
		cout << "Create mutex failed." << endl;
		cout << "Press any key to exit." << endl;
		cin.get();

		return GetLastError();
	}


	HANDLE hSemaphore;
	wchar_t lpSemaphore[] = L"Semaphore";
	hSemaphore = CreateSemaphore(NULL, 2, 2, lpSemaphore);
	if (hSemaphore == NULL)
		return GetLastError();

	cout << "Enter count of Writer process ";
	int count_of_Writer_process; 
	cin >> count_of_Writer_process;

	

	for (int i = 0; i < count_of_Writer_process; i++)
	{

		ZeroMemory(&si1, sizeof(STARTUPINFO));
		si1.cb = sizeof(STARTUPINFO);

		strcat(lpszComLine1, lpszHandle1);
		MultiByteToWideChar(CP_ACP, 0, lpszComLine1, strlen(lpszComLine1), wComLine, 160);

		CreateProcess(NULL, wComLine, NULL, NULL, FALSE, CREATE_NEW_CONSOLE, NULL, NULL, &si1, &pi1);

		/*dwWaitResult = WaitForSingleObject(hInEvent, INFINITE);
		if (dwWaitResult != WAIT_OBJECT_0)
			return dwWaitResult;
		cout << "Process1 is done ";*/
	

		
//
		//ReleaseSemaphore(hSemaphore, 1, NULL);
        ZeroMemory(&si2, sizeof(STARTUPINFO));
		si2.cb = sizeof(STARTUPINFO);
		strcat(lpszComLine2, lpszHandle2);
		MultiByteToWideChar(CP_ACP, 0, lpszComLine2, strlen(lpszComLine2), wComLine, 160);
		CreateProcess(NULL, wComLine, NULL, NULL, FALSE, CREATE_NEW_CONSOLE, NULL, NULL, &si2, &pi2);

		ZeroMemory(&si3, sizeof(STARTUPINFO));
		si3.cb = sizeof(STARTUPINFO);
		CreateProcess(NULL, wComLine, NULL, NULL, FALSE, CREATE_NEW_CONSOLE, NULL, NULL, &si3, &pi3);

		/*dwWaitResult = WaitForSingleObject(hInEvent, INFINITE);
		if (dwWaitResult != WAIT_OBJECT_0)
			return dwWaitResult;
		cout << "Process2 is done ";*/
		
	}
	for (int i = 0; i < count_of_Writer_process*3; i++)
	{
		dwWaitResult = WaitForSingleObject(hInEvent, INFINITE);
	}

	WaitForSingleObject(pi1.hProcess, INFINITE);
	CloseHandle(hMutex);
	CloseHandle(hInEvent);
	CloseHandle(hSemaphore);
	CloseHandle(pi1.hThread);
	CloseHandle(pi1.hProcess);
	return 0;
}
