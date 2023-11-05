Написать программу для консольного процесса, который состоит из двух потоков: main и worker.

Поток main должен выполнить следующие действия:
1. Создать массив целых чисел, размерность и элементы которого вводятся с консоли( или сгенерировать
случайно);
2. Ввести время для остановки и запуска потока worker;
3. Создать поток worker;
4. Приостановить поток worker(SuspendThread), затем через некоторое время снова запустить поток;
5. Дождаться завершения потока worker;
6. Вывести на консоль результат работы потока worker;
7. Завершить работу.

Поток worker должен выполнить следующую работу:
Найти максимальный элемент массива. Завершить свою работу.
