#define _CRT_SECURE_NO_WARNINGS
#include <conio.h>
#include <Windows.h>
#include <iostream>
#include <string>


using namespace std;
int main()
{
	char lpszComLine[500] = "ConsoleApplication2.exe ";
	char lpszHandle[20];
	wchar_t wComLine[180];

	char str[500];
	char str3[] = " ";

	cout << "Enter array size ";
	cin.getline(str, 500);
	int s = stoi(str) + 2;
	string size = to_string(s);
	strcpy(str, "");
	strcat(str, size.c_str());
	strcat(lpszComLine, str);
	cout << endl<<"Enter array "<<endl;
	strcpy(str, "");
	cin.getline(str, 500);
	strcat(lpszComLine, " ");
	strcat(lpszComLine, str);
	strcpy(str, "");
	cout << endl << "enter the search range (2 numbers) " << endl;
	cin.getline(str, 500);
	strcat(lpszComLine, " ");
	strcat(lpszComLine, str);
	
    //cout << lpszComLine;
	STARTUPINFO si;
	PROCESS_INFORMATION pi;

	ZeroMemory(&si, sizeof(STARTUPINFO));
	si.cb = sizeof(STARTUPINFO);

	si.dwX = 400; // this member is the x offset of the upper left corner of a window if a new window is created

	strcat(lpszComLine, lpszHandle);
	MultiByteToWideChar(CP_ACP, 0, lpszComLine, strlen(lpszComLine), wComLine, 160);

	CreateProcess( NULL, wComLine, NULL, NULL, TRUE, CREATE_NEW_CONSOLE, NULL, NULL, &si, &pi);

	WaitForSingleObject(pi.hProcess, INFINITE);

	CloseHandle(pi.hProcess);
	CloseHandle(pi.hThread);

	return 0;
}
