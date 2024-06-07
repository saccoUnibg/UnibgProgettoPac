<template>
    <div align = "center">
        <h1>Elimina prenotazione</h1>
        <hr>
    </div>

    <form @submit.prevent="eliminaConferma(volo)">
        <div class="container" align = "center">

            <h4>Data: </h4>
            <p :text="volo.data"></p>

            <p><b>Partenza: </b></p>
            <p :text="volo.partenza"></p>

            <h4>Arrivo: </h4>
            <p :text="volo.arrivo"></p>

            <h4>Ora Partenza: </h4>
            <p :text="volo.h_partenza"></p>

            <hr>

            <h2>La prenotazione per il seguente volo verrà eliminata.</h2><br>
            <h2>Confermare?</h2>

            <button type="submit" class="registerbtn">Conferma!</button> <br><br>
            <button @click="this.$router.push('/ProfileHomePage')">Annulla e torna alla homepage</button>

        </div>
        <br><br>
        <hr>
    </form>
</template>

<script>
    import axios from 'axios'

    export default {
        name: 'EliminaPrenotazione',
        props: {
            volo: null
        },
        methods: {
            async eliminaConferma(volo) {
                axios.post('http://localhost:8080/prenotazioni/elimina/conferma', volo)
                .then(response => {
                          console.log(response);
                          this.message = response.data.nome;
                          //this.eliminaSuccess(response.data);
                        })
                .catch(error => {
                    console.error(error);
                    this.message = "Login fallita";
                });
            },
            eliminaSuccess(response){
                console.log(response);
                this.$router.push({
                    name: 'ProfileHomePage'
                })
                .catch(error => console.error(error));
            }
        }
    }
</script>
