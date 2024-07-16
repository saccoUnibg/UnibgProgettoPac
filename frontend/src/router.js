import { createRouter, createWebHistory } from 'vue-router';

import HomePage from './components/HomePage.vue';
import LoginPage from './components/login/Login.vue';
import LogoutPage from './components/login/Logout.vue';
import SingUpPage from './components/login/Registrazione.vue';
import SingUpSuccess from './components/login/RegistrazioneSuccess.vue';
import SingUpFail from './components/login/RegistrazioneFail.vue';
import ProfileHomePage from './components/login/ProfileHomePage.vue';
import ModificaAnagrafica from './components/anagrafica/ModificaAnagrafica.vue';
import ModificaAnagraficaSuccess from './components/anagrafica/ModificaAnagraficaSuccess.vue';
import RicercaVoli from './components/voli/RicercaVoli.vue';
import VisualizzaPrenotazioni from './components/prenotazione/VisualizzaPrenotazioni.vue';
import EliminaPrenotazione from './components/prenotazione/EliminaPrenotazione.vue';
import EliminaSuccess from './components/prenotazione/EliminaPrenotazioneSuccess.vue';
import CheckIn from './components/prenotazione/CheckIn.vue';
import CreaPrenotazione from './components/prenotazione/CreaPrenotazione.vue';

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
            path: '/singupSuccess',
            name: 'SingUpSuccess',
            component: SingUpSuccess
        },
        {
            path: '/singupFail',
            name: 'SingUpFail',
            component: SingUpFail
        },
        {
            path: '/profilehomepage',
            name: 'ProfileHomePage',
            component: ProfileHomePage
        },
        {
            path: '/modifica',
            component: ModificaAnagrafica
        },
        {
            path: '/modificaSuccess',
            component: ModificaAnagraficaSuccess
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
        },
        {
            path: '/eliminaPrenotazione',
            name: 'EliminaPrenotazione',
            component: EliminaPrenotazione
        },
        {
            path: '/eliminaPrenotazioneSuccess',
            name: 'EliminaSuccess',
            component: EliminaSuccess
        }
        ,{
            path: '/creaPrenotazione',
            name: 'CreaPrenotazione',
            component: CreaPrenotazione
        }
        ,{
            path: '/checkIn',
            name: 'CheckIn',
            component: CheckIn
        }


    ]
});

export default router;