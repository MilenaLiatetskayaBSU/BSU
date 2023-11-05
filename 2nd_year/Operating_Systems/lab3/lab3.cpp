#include <iostream>
#include <windows.h>
#include <vector>
#include <algorithm>

using namespace std;

int len;
long int* mas;
long int* res;
long int result = 1;

HANDLE WorkEvent;
HANDLE MultElementEvent;
CRITICAL_SECTION cs;

DWORD WINAPI Work(LPVOID lpParam)
{

	int time;
	cout << endl << "Enter time "; cin >> time;

	long int sqrt_; long int sum;
	int count_ = 0;

	for (int i = 0; i < len; i++)
	{
		sqrt_ = sqrt(mas[i]);
		if (sqrt_ * sqrt_ == mas[i])
		{
			sum = 0;
			for (int j = 1; j <= sqrt_ / 2; j++)
			{
				if (sqrt_ % j == 0)
				{
					sum += j;
				}
			}

			if (sum == 1)
			{
				res[count_] = mas[i];
				count_++;
				Sleep(time);
			}
		}

	}

	SetEvent(WorkEvent);

	return 0;
}

DWORD WINAPI MultElement(LPVOID lpParam)
{
	EnterCriticalSection(&cs);
	if (res[0] == 0)
	{
		cout << endl << "There are no elements in the result array";
		LeaveCriticalSection(&cs);
		return 0;
	}

	for (int i = 0; i < len; i++)
	{
		if (res[i] > 0)
		{
			result *= res[i];
		}

		else
		{
			break;
		}
	}

	LeaveCriticalSection(&cs);
	SetEvent(MultElementEvent);

	return 0;
}

int main()
{
	WorkEvent = CreateEvent(NULL, FALSE, FALSE, NULL);
	InitializeCriticalSection(&cs);
	if (WorkEvent == NULL)
	{
		return GetLastError();
	}

	MultElementEvent = CreateEvent(NULL, FALSE, FALSE, NULL);
	if (MultElementEvent == NULL)
	{
		return GetLastError();
	}

	cout << "Enter array size " << endl;
	cin >> len;
	mas = new long int[len];
	cout << "Enter the elements of the array ";
	long int numb;
	for (int i = 0; i < len; i++)
	{
		cin >> numb;
		mas[i] = numb;
	}
	cout << endl << "Size of entered array - " << len;
	cout << endl << "Entered elements - ";
	for (int i = 0; i < len; i++)
	{
		cout << mas[i] << " ";
	}

	res = new long int[len];

	for (int i = 0; i < len; i++)
	{
		res[i] = 0;
	}

	HANDLE hThread[2];
	DWORD dwThread[2];

	hThread[0] = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)Work, NULL, 0, &dwThread[0]);
	if (hThread[0] == NULL)
	{
		return GetLastError();
	}

	EnterCriticalSection(&cs);
	hThread[1] = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)MultElement, NULL, 0, &dwThread[1]);
	if (hThread[1] == NULL)
	{
		return GetLastError();
	}
	WaitForSingleObject(WorkEvent, INFINITE);

	cout << endl << "Final array: ";
	for (int i = 0; i < len; i++)
	{
		cout << res[i] << " ";
	}

	LeaveCriticalSection(&cs);

	WaitForSingleObject(MultElementEvent, INFINITE);

	cout << endl << "Result of multiplying the elements of the final array - " << result << endl;

	DeleteCriticalSection(&cs);
	CloseHandle(WorkEvent);
	CloseHandle(MultElementEvent);
	return 0;
}