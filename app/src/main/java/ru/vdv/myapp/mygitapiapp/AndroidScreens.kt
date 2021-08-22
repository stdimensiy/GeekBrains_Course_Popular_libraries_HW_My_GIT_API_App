package ru.vdv.myapp.mygitapiapp

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.vdv.myapp.mygitapiapp.imageconverter.ImageConverterFragment
import ru.vdv.myapp.mygitapiapp.interfaces.IScreens
import ru.vdv.myapp.mygitapiapp.userInfo.UserInfoFragment
import ru.vdv.myapp.mygitapiapp.users.UsersFragment

/**
 * Класс объявления экранов"
 *
 * реализует интерфейс IScreens *
 */
class AndroidScreens : IScreens {
    /**
     *Объявление экрана списка пользователей (главный на текущий момпент)
     *@return возвращает экран (Screen) соответствующего фрагмента
     */
    override fun users(): Screen = FragmentScreen { UsersFragment.newInstance() }

    /**
     *Объявление экрана детализации информации о пользователе
     *@param userLogin - уникальный логин пользователя в базе (String) всегда в нижнем регистре
     *@return возвращает экран (Screen) соответствующего фрагмента
     */

    override fun userInfo(userLogin: String): Screen =
        FragmentScreen { UserInfoFragment.newInstance(userLogin) }

    /**
     *Объявление экрана конвертации изображений
     *@return возвращает экран (Screen) соответствующего фрагмента
     */

    override fun imageConverter(): Screen =
        FragmentScreen { ImageConverterFragment() }
}