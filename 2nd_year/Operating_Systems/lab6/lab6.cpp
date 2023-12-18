#include <thread>
#include <iostream>
#include <mutex>
#include <chrono>
#include <atomic>
#include <condition_variable>

using namespace std;

int len;
long int* mas;
long int* res;
long int result = 1;

mutex g_lock;
std::condition_variable_any Work_c;
std::mutex Work_m;

std::condition_variable_any Mult_c;
std::mutex Mult_m;

void Work()
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
				std::this_thread::sleep_for(std::chrono::milliseconds(time));
			}
		}

	}

	Work_c.notify_one();
}

void MultElement()
{
	g_lock.lock();
	if (res[0] == 0)
	{
		cout << endl << "There are no elements in the result array";
		g_lock.unlock();
		return;
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

	g_lock.unlock();
	Mult_c.notify_one();
}

int main()
{
	srand((unsigned int)time(0));
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

	thread t1(Work);

	g_lock.lock();
	thread t2(MultElement);

	std::unique_lock<std::mutex> lk(Work_m);
	Work_c.wait(lk);

	cout << endl << "Final array: ";
	for (int i = 0; i < len; i++)
	{
		cout << res[i] << " ";
	}

	g_lock.unlock();

	std::unique_lock<std::mutex> lk2(Mult_m);
	Mult_c.wait(lk2);

	cout << endl << "Result of multiplying the elements of the final array - " << result << endl;

    t1.join();
    t2.join();
}

