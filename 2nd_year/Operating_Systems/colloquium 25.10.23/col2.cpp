
#include <iostream>

using namespace std;

int reverse_number(int number)
{
	int n = 0;
	while (number)
	{
		n = 10 * n + number % 10;
		number /= 10;
	}

	return n;
}

bool Check(int number)
{
	if (number == reverse_number(number))
	{
		return 1;
	}

	else
	{
		return 0;
	}
}

int main()
{
	double number;
	cin >> number;


	bool answer = Check(number);

	if (answer == 1)
	{
		cout << "Yes";
	}

	else
	{
		cout << "No";
	}
}

