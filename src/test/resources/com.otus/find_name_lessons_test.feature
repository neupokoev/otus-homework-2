#language: ru

    @api
    Функционал: Страница

        Сценарий: Посчитать все курсы на главной
            Пусть Открыта главная страница в браузере "chrome"
            Если Закрыт баннер с куки
            Тогда Посчитать количество курсов на странице
            #* Проверить что количество курсов оказалось больше "10"