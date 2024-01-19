
#include <iostream>
#include <vector>
using namespace std;


vector<int>GetResult(int value)
{
	vector<int> res;
	res.resize(value+1);

	if (value == 0)
	{
		res[0] = 1;
		return res;
	}

	res[0] = 1;
	for (int i = 1; i <= value; i++)
	{
		res[i] = res[i - 1] * i;
	}
	return res;
}


int main()
{
	int value;

	try {
		cin >> value;
	
	vector<int>result(value+1);
	result = GetResult(value);

	for (int i : result)
	{
		cout << i << " ";
	}
	}catch (const exception& e) {
		cerr << "Invalid size";
		return 1;
	}
	return 0;
}
