<template>
    <div align="center">
        <form @submit.prevent="checkInConfirm()">
            <table>
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>CF</th>
                    <th>ID Documento</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="checkin in checkinList" :key="checkin">
                    <td><input type="text" v-model="checkin.nome" required/></td>
                    <td><input type="text" v-model="checkin.cognome" required/></td>
                    <td><input type="text" v-model="checkin.cf" required/></td>
                    <td><input type="text" v-model="checkin.id_documento" required/></td>
                </tr>
                </tbody>
            </table>
            <button type="submit">Submit</button>
        </form>
    </div>

    <div align="center">
        <button @click="this.$router.push('/ProfileHomePage')">Homepage</button>
    </div>
</template>

<script>
    export default {
        props: {

        },
        data()  {
            return {
                checkInCount: Number,
                checkinList: [],
            }
        },
        created() {
            console.log("Check In");
            this.checkInCount = this.$route.query.checkInCount;
            for (let i = 0; i < this.checkInCount; i++) {
                this.checkinList.push({
                    nome: '',
                    cognome: '',
                    cf: '',
                    id_documento: '',
                    idPrenotazione: '',
                });
            }
            console.log(this.checkInCount);
            console.log(this.checkinList);
        },
        methods: {
            async checkInConfirm() {
                this.axios.post('/prenotazioni/success', {checkinList:this.checkinList})
                .then(response => {
                          console.log(response);
                          this.checkInConfirmed(response.data);
                        })
                .catch(error => {
                    console.error(error);
                });
            },
            checkInConfirmed(response){
                console.log(response);
                this.$router.push({
                    name: 'CheckInSuccess'
                })
                .catch(error => console.error(error));
            }
        }
    }
</script>
