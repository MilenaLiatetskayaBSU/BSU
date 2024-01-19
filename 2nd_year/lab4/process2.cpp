#define _CRT_SECURE_NO_WARNINGS
#include <conio.h>
#include <iostream>
#include <Windows.h>
#include <string>

using namespace std;


HANDLE hInEvent;
wchar_t lpEventName[] = L"Event1";
HANDLE hSemaphore;
wchar_t lpSemaphore[] = L"Semaphore";
HANDLE hOutEvent[2];

wchar_t hOutEvent1[] = L"ONE";
wchar_t hOutEvent2[] = L"TWO";

int main(int argc, char* argv[])
{
    cout << "Reader is open" << endl;
    hInEvent = OpenEvent(EVENT_MODIFY_STATE, FALSE, lpEventName);
    if (hInEvent == NULL)
    {
        cout << "Open event failed." << endl;
        cout << "Press any key to exit." << endl;
        cin.get();

        return GetLastError();
    }

    hSemaphore = OpenSemaphore(SEMAPHORE_ALL_ACCESS, FALSE, lpSemaphore);
    if (hSemaphore == NULL)
    {
        cout << "Open Semaphore failed." << endl;
        cout << "Press any key to exit." << endl;
        cin.get();

        return GetLastError();
    }
    WaitForSingleObject(hSemaphore, INFINITE);
    cout << " Active " << endl;
    hOutEvent[1] = OpenEvent(EVENT_ALL_ACCESS, FALSE, hOutEvent2);
    if (hOutEvent[1] == NULL)
    {
        cout << "Open event failed." << endl;
        cout << "Press any key to exit." << endl;
        cin.get();

        return GetLastError();
    }

    hOutEvent[0] = OpenEvent(EVENT_ALL_ACCESS, FALSE, hOutEvent1);
    if (hOutEvent[0] == NULL)
    {
        cout << "Open event failed." << endl;
        cout << "Press any key to exit." << endl;
        cin.get();

        return GetLastError();
    }
    DWORD  dwEvent;
    cout << "Message 1" << endl;
   
        dwEvent = WaitForMultipleObjects(2, hOutEvent, FALSE, INFINITE);
        switch (dwEvent)
        {
        case WAIT_OBJECT_0 + 0:
            cout << " A " << endl;
            //ResetEvent(hOutEvent[0]);
            break;
        case WAIT_OBJECT_0 + 1:
            cout << " B " << endl;
           // ResetEvent(hOutEvent[1]);
        }
        
    _getch();
    SetEvent(hInEvent);
    ReleaseSemaphore(hSemaphore, 1, NULL);
    CloseHandle(hInEvent);
    CloseHandle(hOutEvent);
}


