<template>
    <div>
        <form @submit.prevent="checkIn()">
            <h1>Prenotazione</h1>
            <div v-if="scalo" class="container" align="center">
                <b>Partenza: </b>
                <i>{{volo.object1.partenza}}</i>
                <br><br>
                <b>Arrivo: </b>
                <i>{{volo.object1.arrivo}}</i>
                <br><br>
                <b>Compagnia: </b>
                <i>{{volo.object1.compagnia}}</i>
                <br><br>

                <b>Partenza: </b>
                <i>{{volo.object2.partenza}}</i>
                <br><br>
                <b>Arrivo: </b>
                <i>{{volo.object2.arrivo}}</i>
                <br><br>
                <b>Compagnia: </b>
                <i>{{volo.object2.compagnia}}</i>
                <br><br>

                <b>Prezzo: </b>
                <i>{{(parseInt(volo.object1.prezzo) + parseInt(volo.object2.prezzo)) * numero_biglietti}}</i>
                <br><br>
                <hr>
            </div>
            <div v-else>
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
                scalo: false,
                volo: null
            }
        },
        created() {
            console.log("CreaPrenotazione");
            this.volo = JSON.parse(localStorage.getItem('voloDaPrenotare'));
            this.scalo = this.$route.query.scalo ?? false;
        },
        methods: {
            async checkIn() {
                axios.post('http://localhost:8080/'+(this.scalo?'prenotazioniScalo':'prenotazioni')+'/check-in', {id:"0", idVolo: this.volo.id, numero_biglietti: this.numero_biglietti, mail:"0", spesa_totale: this.volo.prezzo * this.numero_biglietti})
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

