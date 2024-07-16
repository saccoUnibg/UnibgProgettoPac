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
    export default {
        name: 'VisualizzaPrenotazioni',
        data()  {
            return {
                listaVoli: [],
            }
        },
        created() {
            console.log("VisualizzaPrenotazioni");
            this.axios.get('/prenotazioni/visualizza')
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
                this.axios.post('/prenotazioni/elimina', volo)
                .then(response => {
                          console.log(response);
                          this.eliminaConferma(volo);
                        })
                .catch(error => {
                    console.error(error);
                });
            },
            eliminaConferma(volo){
                console.log(volo);
                localStorage.setItem('voloDaEliminare', JSON.stringify(volo));
                this.$router.push({
                    name: 'EliminaPrenotazione'
                })
                .catch(error => console.error(error));
            }
        }
    }
</script>
