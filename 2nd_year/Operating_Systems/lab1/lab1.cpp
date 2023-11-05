#include <Windows.h>
#include <iostream>
#include <process.h>
using namespace std;


struct res
{
	int* mas;
	int res;
	int count;
};

DWORD WINAPI Worker ( LPVOID  rez)
{
	res* rezult = (res*)rez;
	int max = rezult->mas[0];
	for (int i = 1; i < (int)rezult->count; i++)
	{
		if (rezult->mas[i] > max)
		{
			max = rezult->mas[i];
		}
	}
	rezult->res = max;

	return max;
}


int main()
{
	setlocale(0, "Russian");
	HANDLE hThread;
	DWORD IDThread;
	srand(time(NULL));
	res rezult;
	cout << "Enter array size" << endl;
	cin >> rezult.count; cout << endl;
	rezult.mas = new int[rezult.count];
	for (int i = 0; i < rezult.count; i++)
	{
		rezult.mas[i] = rand() % 40;
	}

	for (int i = 0; i < rezult.count; i++)
	{
		cout << rezult.mas[i] << " ";
	}
	hThread = CreateThread(NULL, 0, Worker, &rezult, 0, &IDThread);
	if (hThread == NULL)
		return GetLastError();
	if (hThread != NULL) 
	{
		SuspendThread(hThread);
		Sleep(1000);
		ResumeThread(hThread);
	}
	WaitForSingleObject(hThread, INFINITE);
	CloseHandle(hThread);
	cout << endl << rezult.res;
}