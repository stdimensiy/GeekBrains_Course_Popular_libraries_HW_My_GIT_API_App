package ru.vdv.myapp.mygitapiapp

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.vdv.myapp.mygitapiapp.interfaces.IScreens
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
}