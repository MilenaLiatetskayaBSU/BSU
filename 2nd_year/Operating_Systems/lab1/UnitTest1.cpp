#include "pch.h"
#include "CppUnitTest.h"
#include "../lab1/lab1.cpp"

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTest1
{
	TEST_CLASS(UnitTest1)
	{
		
	public:
		TEST_METHOD(TestMethod1)
		{
			int expected = 5;

			std::stringstream input("4 1 2 3 5");
			std::stringstream output;

			cin.rdbuf(input.rdbuf());
			cout.rdbuf(output.rdbuf());
			int a = main();

			Assert::AreEqual(expected, a);
		}
	};
}
