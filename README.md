Правила работы с этим репозиторием

В начале работы открываем консоль, переходим в рабочую директорию, ПАПКУ ДЛЯ ПРОЕКТА НЕ СОЗДАЁМ, она будет создана автоматически! Делаем

git clone АДРЕС ЭТОГО РЕПОЗИТОРИЯ

Переключаемся на ветку develop:

git checkout develop

и удаляем локальную ветку мастер, она нам не понадобится:

git branch -d master

Успех! У Вас на машине копия репозитория!!!

Перед тем, как что-то поменять в скачанных файлах, сделайте следующее:

git status

// должно написать, что у вас ничего не изменилось, никаких modifyed, deleted!!!

// если результат отличается, больше руками ничего не трогать, писать мне (Сергею)

Далее делаем

git fetch origin

// чтобы скачать все изменения с сервера на локальную машину.

git checkout -b NAME_OF_YOUR_NEW_BRANCH origin/develop

// вместо NAME_OF_YOUR_NEW_BRANCH пишем название ветки, в котором должно

// отражаться содержание того, что Вы хотите сделать

git branch -d develop

// не стоит хранить ветки, которые не используете в данный момент.

git status

// убеждаемся, что всё ок, Вы в своей новой ветке.

Правите файлы, добавляете новые, делаете всё, что считаете нужным и полезным для проекта в СВОЕЙ ВЕТКЕ!!!

Затем пишем

git status

// выведется куча изменённых, добавленных и удалённых файлов 

// если Вы добавили новые файлы, нужно написать 

git add путь/к/новому.файлу путь/ко/второму/новому/файлу

git status

// новые файлы зелёные, всё хорошо, изменённые старые -- красные

git commit -am "Я исправил файл a.cpp, удалил b.h, добавил c.h"

// в кавычках -- Ваш комментарий

Когда Вы Сделали всё, что хотели и довольны своей работой, считаете, что Вы сделали всё возможное в этой ветке, делаете следующее:

git push origin NAME_OF_YOUR_NEW_BRANCH

git status

// убеждаемся, что всё "up_to_date"

На этом можно остановиться, сообщить коллегам, что Вы добавили новую функциональность в новую ветку, и на это дело надо посмотреть и потестить. Далее можно переходить к пункту, где мы наслаждаемся победой!

Другие не спали, что-то поменяли, нужно это скачать:

git fetch origin

git checkout develop

git status

// всё ок

git merge NAME_OF_YOUR_NEW_BRANCH

// тут он будет совмещать результаты Ваших трудов с тем, что сделали другие

// в результате он выдаст сообщение со списком файлов, которые нужно слить

// вручную. Открываем их, смотрим, что он нам понадобавлял, правим. (поиск по <<<<< )

Если получилось сделать автоматическое слияние, git предложит отредоктировать сообщение, можно оставить как есть.

После того, как просмотрели и исправили все проблемные файлы, проверяем, всё ли работает, исправляем дальше, пока не заработает, делаем коммит:

git commit -a
// редактируем сообщение, вернее, соглашаемся с тем, что предложит git

git push origin develop

git status

Наслаждаемся победой)))

После этого повторять все шаги, начиная с 

git status

git fetch origin

git checkout -b NAME_OF_YOUR_NEW_BRANCH origin/develop

git branch -d develop
