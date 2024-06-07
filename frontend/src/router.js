import { createRouter, createWebHistory } from 'vue-router';

import HomePage from './components/HomePage.vue';
import LoginPage from './components/login/Login.vue';
import SingUpPage from './components/login/Registrazione.vue';
import ProfileHomePage from './components/login/ProfileHomePage.vue';
import LogoutPage from './components/login/Logout.vue';
import RicercaVoli from './components/voli/RicercaVoli.vue';
import VisualizzaPrenotazioni from './components/prenotazione/VisualizzaPrenotazioni.vue';

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'HomePage',
            component: HomePage
        },
        {
            path: '/login',
            name: 'LoginPage',
            component: LoginPage
        },
        {
            path: '/singup',
            name: 'SingUpPage',
            component: SingUpPage
        },
        {
            path: '/profilehomepage',
            name: 'ProfileHomePage',
            component: ProfileHomePage
        },
        {
             path: '/logout',
             name: 'LogoutPage',
             component: LogoutPage
        },
        {
             path: '/ricerca',
             name: 'RicercaVoli',
             component: RicercaVoli
        },
        {
            path: '/visualizza',
            name: 'VisualizzaPrenotazioni',
            component: VisualizzaPrenotazioni
        }


    ]
});

export default router;