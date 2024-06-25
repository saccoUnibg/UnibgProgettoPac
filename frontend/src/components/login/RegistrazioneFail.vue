<template>
    <p>Errore nella registrazione: </p>
    <ul th:text="${error}"></ul>
</template>

<script>
export default {
    name: 'SingUpFail',
    created() {
      setTimeout(() => {
          this.$router.push("/"); //aspetta 2 secondi poi torna in homepage
      }, 2000);
    }
}
</script>