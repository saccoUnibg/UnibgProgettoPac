<template>
    <div align="center">
        <h1>Prenotazioni</h1>
        <h3>Da qui puoi vedere e cancellare le prenotazioni effettuate</h3> <br> <br> <hr>
    </div>

    <table align="center">
        <thead>
        <tr>
            <th>Data</th>
            <th>Partenza</th>
            <th>Arrivo</th>
            <th>Ora Partenza</th>
            <th>ID Volo</th>
        </tr>
        </thead>
        <tbody>
        <div>
            <tr v-if="typeof listaVoli === 'undefined' || listaVoli.length === 0">
                <td colspan="2">Nessun volo prenotato</td>
            </tr>
        </div>

        <tr v-for="volo in listaVoli" :key="volo.id" align="center">
            <td>{{volo.data}}</td>
            <td>{{volo.partenza}}</td>
            <td>{{volo.arrivo}}</td>
            <td>{{volo.h_partenza}}</td>
            <td>{{volo.id}}</td>
            <td>
                <div align="center">
                    <form @submit.prevent="elimina(volo)">
                            <button type="submit">Elimina</button>
                    </form>
                </div>
            </td>
        </tr>
        </tbody>
    </table>

<br><hr><br><br>

    <div align="center">
        <button @click="this.$router.push('/ProfileHomePage')">Homepage</button>
    </div>

</template>

<script>
    import axios from 'axios'

    export default {
        name: 'VisualizzaPrenotazioni',
        data()  {
            return {
                listaVoli: [],
                showModal : false,
            }
        },
        created() {
            console.log("VisualizzaPrenotazioni");
            axios.get('http://localhost:8080/prenotazioni/visualizza')
                .then(response => {
                          console.log(response);
                          this.listaVoli = response.data;
                        })
                .catch(error => {
                    console.error(error);
            });
        },
        methods: {
            async elimina(volo) {
                axios.post('http://localhost:8080/prenotazioni/elimina', volo)
                .then(response => {
                          console.log(response);
                          //this.eliminaConferma(volo);
                          this.showModal = true;
                        })
                .catch(error => {
                    console.error(error);
                    this.message = "Login fallita";
                });
            },
            eliminaConferma(response){
                console.log(response);
                this.$router.push({
                    name: 'EliminaPrenotazione'
                })
                .catch(error => console.error(error));
            }
        }
    }
</script>
