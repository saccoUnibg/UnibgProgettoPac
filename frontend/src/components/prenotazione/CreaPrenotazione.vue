<template>
    <div>
        <form @submit.prevent="checkIn()">
            <div class="container" align="center">
                <h1>Prenotazione</h1>
                <b>Partenza: </b>
                <i>{{volo.partenza}}</i>
                <br><br>
                <b>Arrivo: </b>
                <i>{{volo.arrivo}}</i>
                <br><br>
                <b>Compagnia: </b>
                <i>{{volo.compagnia}}</i>
                <br><br>
                <b>Prezzo: </b>
                <i>{{volo.prezzo * numero_biglietti}}</i>
                <br><br>
                <hr>
            </div>
            <hr>
            <div class="container" align="center">
                <label>Numero Biglietti</label><br>
                <input type="number" min="1" max="3" v-model="numero_biglietti" size="20"/> <br>
                <button type="submit">Avanti</button>
            </div>
        </form>
    </div>

    <div align="center">
        <button @click="this.$router.push('/ProfileHomePage')">Homepage</button>
    </div>

</template>

<script>
    import axios from 'axios'

    export default {
        data()  {
            return {
                numero_biglietti: 1,
                volo: null
            }
        },
        created() {
            console.log("CrePrenotazione");
            this.volo = JSON.parse(localStorage.getItem('voloDaPrenotare'));
        },
        methods: {
            async checkIn() {
                axios.post('http://localhost:8080//prenotazioni/check-in', {numero_biglietti: this.numero_biglietti})
                .then(response => {
                          console.log(response);
                          this.CheckIn(response.data);
                        })
                .catch(error => {
                    console.error(error);
                });
            },
            CheckIn(response){
                console.log(response);
                this.$router.push({
                    name: 'CreaPrenotazione'
                })
                .catch(error => console.error(error));
            }
        }
    }
</script>

