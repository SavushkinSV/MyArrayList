Общие требования:

1. Сделать ArrayList или HashMap.
2. Использовать Java версии 21.
3. Коллекция должна работать с любым типом объектов.
4. Документировать код. Что, зачем и как. Все классы и интерфейсы должны быть задокументированы на уровне класса (class-level javadoc) Все публичные методы ваших реализаций должны содержать javadoc. Документация должна быть в полном объёме и представлять исчерпывающее и интуитивно понятное руководство пользования вашим кодом для другого разработчика. Статья по [Javadoc](https://www.baeldung.com/javadoc).
5. Все части кода должны быть покрыты Unit тестами(Использовать [JUnit](https://habr.com/ru/articles/590607/)).
6. Провести проверки с помощью циклов (добавить 1000 объектов, удалить 1000 итд, числа для примера).
7. Использовать maven или gradle для добавления JUnit.
8. .idea не должно быть в репозитории(добавляем в gitignore).
9. Работу выкладывать на GitHub в публичный репозиторий, все изменения должны быть в master ветке.
10. Работать с [Git](https://habr.com/ru/articles/541258/) нужно через консоль.
11. Содержать код в [чистоте](https://www.youtube.com/watch?v=otrfSgeK3JI).

Требования к ArrayList:
1. Реализовать свой ArrayList (не потокобезопасный) Методы - добавить элемент, добавить элемент по индексу (не заменить), получить элемент, удалить элемент, очистить всю коллекцию, отсортировать, заменить элемент по индексу, остальное по желанию.
2. Реализовать алгоритм quicksort для реализованной вами реализации ArrayList. Использовать: Обобщенные типы, Comparable, Comparator. Не должно быть ограничение на Comparable класс в ArrayList. Сортировка может быть встроенной в ваш ArrayList или быть внешним классом.
3. (Встроенные сортировки не считаются, нужна именно своя реализация).

Задание HashMap:
1. Сделать свою реализацию HashMap (не потокобезопасный) с основными методами get, put, delete, values, keyset, entryset.
