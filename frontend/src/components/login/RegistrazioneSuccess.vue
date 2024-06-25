<template>
    <div align="center">
        <p>Registrazione avvenuta con successo!</p>
    </div>
</template>

<script>
export default {
    name: 'SingUpSuccess',
    created() {
      setTimeout(() => {
          this.$router.push("/"); //aspetta 2 secondi poi torna in homepage
      }, 2000);
    }
}
</script>