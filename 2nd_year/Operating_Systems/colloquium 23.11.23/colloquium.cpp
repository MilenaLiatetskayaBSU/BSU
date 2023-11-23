#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <thread>
#include <mutex>

using namespace std;

mutex result_mutex;

double result = 0.0;

void processFile(const string& filename)
{
    ifstream file(filename);
    if (file.is_open())
    {
        int action;
        file >> action;

        double value;
        double res = 1;
        while (file >> value)
        {
            if (action == 1)
            {

                lock_guard<mutex> lock(result_mutex);
                result += value;
            }
            else if (action == 2)
            {
                lock_guard<mutex> lock(result_mutex);
                res *= value;
            }
            else if (action == 3)
            {
                lock_guard<mutex> lock(result_mutex);
                result += (value * value);
            }
        }
        if (action == 2)
        {
            result += res;
        }

        file.close();
    }
}

int main(int argc, char* argv[])
{
    string directory = argv[1];
    int num_threads = stoi(argv[2]);

    vector<thread> threads;
    for (int i = 1; i <= num_threads; ++i)
    {
        std::string filename = directory + "/in_" + to_string(i) + ".dat.txt";
        threads.emplace_back(processFile, filename);
    }


    for (auto& thread : threads)
    {
        thread.join();
    }

    ofstream result_file(directory + "/out.dat.txt");
    if (result_file.is_open())
    {
        result_file << result << std::endl;
        result_file.close();
    }
    else
    {
        cout << "Failed to open result file" << std::endl;
        return 1;
    }

    return 0;
}

