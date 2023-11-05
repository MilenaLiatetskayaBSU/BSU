#define _CRT_SECURE_NO_WARNINGS
#include <conio.h>
using namespace std;
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
int main(int argc, char* argv[])
{
	int size = stoi(argv[1]);
	int* mas = new int[size];
	int k = 2;
	int i = 0;
	for (i; i < size; i++)
	{
		mas[i] = stoi(argv[k]);
		k++;
		cout << mas[i] << " ";
	}

	int A = mas[size - 2];
	int B = mas[size - 1];

	size = size - 2;

	int* mas2 = new int[size];

	for (int i = 0; i < size; i++)
	{
		mas2[i] = 0;
	}

	k = 0;

	cout << endl<< A << " " << B << endl;

	for (int i = 0; i < size; i++)
	{
		if ((mas[i] <= B) && (mas[i] >= A))
		{
			mas2[k] = mas[i];
			k++;
		}
	}

	for (int i = 0; i < size; i++)
	{
		cout << mas2[i]<<" ";
	}


	_getch();
}


