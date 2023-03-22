# Поиск подстроки

| Данные                              | Алгоритм                | Время, мс |
|-------------------------------------|-------------------------|-----------|
| Повторяющиеся блоки симвлов         | КМП-fast                | 341       |
|                                     | Полное сканнирование    | 1         |
|                                     | БМХ                     | 0         |
|                                     | Использование суффиксов | 0         |
|                                     | String.indexOf          | 0         |
|                                     | Pattern                 | 0         |
| Маска никогда не совпадает          | КМП-fast                | 2         |
|                                     | Полное сканнирование    | 2         |
|                                     | БМХ                     | 0         |
|                                     | Использование суффиксов | 2         |
|                                     | String.indexOf          | 0         |
|                                     | Pattern                 | 0         |
| Не совпадает последний символ маски | КПМ-fast                | 3         |
|                                     | Полное сканнирование    | 1503      |
|                                     | БМХ                     | 4         |
|                                     | Использование суффиксов | 29        |
|                                     | String.indexOf          | 1064      |
|                                     | Pattern                 | 3         |
| Не совпадает первый символ маски    | КПМ-fast                | 2         |
|                                     | Полное сканнирование    | 2         |
|                                     | БМХ                     | 1249      |
|                                     | Использование суффиксов | 1343      |
|                                     | String.indexOf          | 0         |
|                                     | Pattern                 | 1         |


## Описания сгенерированных данных
### Повторяющиеся блоки симвлов
Случайным образом генерируется блок из 1-3 символов A, B, C. Блоки разделяются символом '-'. Так формируется и текст, 
и маска. Таким образом существует вероятность, что маска будет иногда частично или полностью совпадать, а также создаются
более интересные варианты для алгоритма, использующего сдвиги по суффиксам и БМХ.

Размер текста 100000000, размер маски 10

### Маска никогда не совпадает
Текст составлен из повторяющихся символов A, а маска - из B

Размер текста 1000000, размер маски 100

### Не совпадает последний символ маски
Текст составлен из повторяющихся символов A, а маска тоже состоит из символв A, кроме последнего символа, который B

Размер текста 1000000, размер маски 1000

### Не совпадает первый  символ маски
Текст составлен из повторяющихся символов A, а маска тоже состоит из символв A, кроме первого символа, который B

Размер текста 1000000, размер маски 1000

## Алгоритмы
КПМ-fast - быстрое постоение Pi-функции. Алгоритм КПМ с медленным постоением Pi-функции я не стал прогонять, потому что
он работает слишком медленно. Если уменьшать размер данных, то остальные алгоритмы отрабатывают за 0ms, а этот все все
равно не отрабатывает.
String.indexOf использование для поиска станадартной функции из класса String.
Pattern - использование для поиска механизма регулярных выражений. Нашел в интернете, что там реализован поиск Бойера-Мура.
Остальные алгоритмы взяты из предыдущего ДЗ.

# Выводы
КМП очень плохо себя показал на повторяющихся блоках данных