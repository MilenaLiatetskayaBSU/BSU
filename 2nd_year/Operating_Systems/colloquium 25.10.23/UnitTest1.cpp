#include "pch.h"
#include "CppUnitTest.h"
#include "../col1/col1.cpp"
#include <vector>

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTest1
{
	TEST_CLASS(UnitTest1)
	{
		
	public:
		TEST_METHOD(TestMethod1)
		{
			vector<int>result(4);
			result = GetResult(3);

			string expected = "1 1 2 6 ";
			string res = "";

			for (int i : result)
			{
				res += std::to_string(i) + " ";
			}
			Assert::AreEqual(expected, res);
		}

		TEST_METHOD(TestMethod2)
		{
			string expected = "Invalid size";

			std::stringstream input("-5");
			std::stringstream output;

			cin.rdbuf(input.rdbuf());
			cout.rdbuf(output.rdbuf());
			int a = main();

			Assert::AreEqual(expected, output.str().substr(16));
		}
	};
}
