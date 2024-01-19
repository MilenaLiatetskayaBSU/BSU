#include "pch.h"
#include "CppUnitTest.h"
#include "../col2/col2.cpp"

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace UnitTest2
{
	TEST_CLASS(UnitTest2)
	{
	public:
		
		TEST_METHOD(TestMethod1)
		{
			bool result = Check(101);
			bool expected = 1;

			Assert::AreEqual(expected, result);
		}

		TEST_METHOD(TestMethod2)
		{
			bool result = Check(1011);
			bool expected = 0;

			Assert::AreEqual(expected, result);
		}
	};
}
