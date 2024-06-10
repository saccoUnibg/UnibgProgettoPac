<template>
    <div align="center">
        <h2>Prenotazione eliminata con successo!</h2>
        <hr>
        <br>
        <button
                @click="this.$router.push('/visualizza')">Prenotazioni
        </button>
    </div>
    <br>
    <div align="center">
        <button @click="this.$router.push('/ProfileHomePage')">Homepage</button>
    </div>
    <br>
    <hr>
</template>

<script>
    export default {
        name: 'EliminaSuccess'
    }
</script>
