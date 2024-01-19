#define _CRT_SECURE_NO_WARNINGS
#include <conio.h>
#include <iostream>
#include <Windows.h>
#include <string>

using namespace std;

HANDLE hInEvent;
wchar_t lpEventName[] = L"Event1";
HANDLE hOutEvent[2];

wchar_t hOutEvent1[] = L"ONE";
wchar_t hOutEvent2[] = L"TWO";

int main(int argc, char* argv[])
{
    HANDLE	hMutex;
    hMutex = OpenMutex(MUTEX_ALL_ACCESS, FALSE, L"DemoMutex");
    if (hMutex == NULL)
    {
        cout << "Open mutex failed." << endl;
        cout << "Press any key to exit." << endl;
        cin.get();

        return GetLastError();
    }

    WaitForSingleObject(hMutex, INFINITE);
    cout << " Active " << endl;
    hOutEvent[1] = OpenEvent(EVENT_MODIFY_STATE, FALSE, hOutEvent2);
    if (hOutEvent[1] == NULL)
    {
        cout << "Open event failed." << endl;
        cout << "Press any key to exit." << endl;
        cin.get();

        return GetLastError();
    }

    hOutEvent[0] = OpenEvent(EVENT_MODIFY_STATE, FALSE, hOutEvent1);
    if (hOutEvent[0] == NULL)
    {
        cout << "Open event failed." << endl;
        cout << "Press any key to exit." << endl;
        cin.get();

        return GetLastError();
    }

    hInEvent = OpenEvent(EVENT_MODIFY_STATE, FALSE, lpEventName);
    if (hInEvent == NULL)
    {
        cout << "Open event failed." << endl;
        cout << "Press any key to exit." << endl;
        cin.get();

        return GetLastError();
    }
    cout << "mutex is captured";

    cout <<endl<< "Enter number of message ";
    int a;
   // for (int i = 0; i < 2; i++)
   // {
        do {
            cin >> a;
        } while (a != 1 && a != 2);

        if (a == 1)
        {
            SetEvent(hOutEvent[0]);
            Sleep(100);
            ResetEvent(hOutEvent[0]);
        }

        if (a == 2)
        {
            SetEvent(hOutEvent[1]);
            Sleep(100);
            ResetEvent(hOutEvent[1]);
        }
   // }
    
    
         
    
   // _getch();
    SetEvent(hInEvent);
    ReleaseMutex(hMutex);
    CloseHandle(hMutex);
    CloseHandle(hInEvent);
    CloseHandle(hOutEvent);

    //запрашивает с консоли сообщения
    //передает по одному процессам Reader
    //передает сообщение с автоматическим сбросом о завершении процессу Administrator
    //завершает работу
}


