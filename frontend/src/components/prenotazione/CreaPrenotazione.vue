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
            console.log("CreaPrenotazione");
            this.volo = JSON.parse(localStorage.getItem('voloDaPrenotare'));
        },
        methods: {
            async checkIn() {
                axios.post('http://localhost:8080/prenotazioni/check-in', {id:"0", idVolo: this.volo.id, numero_biglietti: this.numero_biglietti, mail:"0", spesa_totale: this.volo.prezzo * this.numero_biglietti})
                .then(response => {
                          console.log(response);
                          this.checkInToConfirm(response.data);
                        })
                .catch(error => {
                    console.error(error);
                });
            },
            checkInToConfirm(response){
                console.log(response);
                console.log(response.checkinList.length);
                this.$router.push({
                    name: 'CheckIn',
                    query: { checkInCount: response.checkinList.length}
                })
                .catch(error => console.error(error));
            }
        }
    }
</script>

