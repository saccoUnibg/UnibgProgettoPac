<template>
    <div align="center">
        <h1>Benvenuto</h1>
        <hr>
        <h2>Anagrafica utente</h2>

        <p><b>Nome: </b></p>
        <p>{{ this.user.nome }}</p>

        <h4>Cognome: </h4>
        <p>{{ this.user.cognome }}</p>

        <h4>Indirizzo e-mail: </h4>
        <p>{{ this.user.mail }}</p>

        <h4>Codice Fiscale: </h4>
        <p>{{ this.user.cf }}</p>

        <hr>
        <button
                @click="this.$router.push('/ricerca')">
            Inizia una nuova ricerca
        </button>
        <br><br>
        <button
                @click="this.$router.push('/visualizza')">
            Visualizza le tue prenotazioni
        </button>
        <br><br>
        <button
                @click="this.$router.push('/modifica')">modifica i tuoi dati
        </button>
        <br><br>
    </div>

    <div align="center">
        <hr>
        <button
                @click="this.$router.push('/logout')">Logout
        </button>
        <hr>
    </div>
</template>

<script>

    export default {
        name: 'ProfileHomePage',
        data()  {
            return {
                user: null,
            }
        },
        created() {
          console.log("ProfileHomePage");
          this.user = JSON.parse(localStorage.getItem('user'));
          console.log(JSON.parse(localStorage.getItem('user')));
        }
    }
</script>