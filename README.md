# Log Analyzer

**Log Analyzer** – проект по эффективной обработке и анализу NGINX-логов с возможностью гибкой фильтрации и формирования отчётов!  

В рамках этого задания я реализовал решение на **Java**, которое:
- Имеет **модульную архитектуру**, благодаря чему код легко расширять и масштабировать под дополнительные требования.
- Использует **JUnit** и **Mockito** для тестирования, что говорит о культуре разработки и стремлении к высокому качеству кода.
- Применяет **Java Collections** и **Stream API** для эффективной обработки логов и расчёта статистики без избыточного потребления памяти.
- Задействует **Lombok** для сокращения шаблонного кода и упрощения поддержки проекта.
- Поддерживает параллелизацию благодаря современным возможностям Java (виртуальные потоки / Project Loom при необходимости).

> **Особенности проекта:**
> - **Современные технологии:** Проект демонстрирует владение актуальным стеком Java, включая тестирование, работу со Streams и коллекциями, а также гибкое модульное устройство.
> - **Эффективная обработка логов:** Использование алгоритмов и структур данных в связке со Stream API позволяет обрабатывать большие объёмы данных без чрезмерной нагрузки на память.
> - **Расширяемость и масштабируемость:** Модульная структура проекта даёт возможность легко добавлять новые функции анализа, форматы отчётов или дополнительные источники логов.
> - **Надёжность и качество:** Тесты на основе JUnit и Mockito повышают стабильность, а чёткая логика обработки исключений делает программу удобной в реальной эксплуатации.
