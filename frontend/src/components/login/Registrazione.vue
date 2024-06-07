<template>
    <div align="center">
        <form action="#" method="post" modelAttribute="utente">
            <div class="container">
                <h1>Registrazione</h1>
                <hr>
                <label> Nome </label>
                <input type="text" name="nome" size="15"/>

                <label> Cognome </label>
                <input type="text" name="cognome" size="15"/>

                <label> Codice fiscale </label>
                <input type="text" name="cf" size="16"/> <br> <br>

                <label><b>Email</b></label>
                <input type="text" placeholder="mail" name="mail" required>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Password" name="psw" id="psw" required>

                <label for="psw-repeat"><b>Ripeti Password</b></label>
                <input type="password" placeholder="Password" name="psw-repeat" id="psw-repeat" required>
                <hr>

                <button type="submit" class="registerbtn">Registrati</button>
            </div>
        </form>
    </div>
</template>

<script>
    export default {
      name: 'SingUpPage'
    }
</script>
