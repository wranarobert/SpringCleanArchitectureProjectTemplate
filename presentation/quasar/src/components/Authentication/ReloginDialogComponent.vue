<template>
  <q-dialog ref="dialog" persistent>
    <q-card class="q-pa-sm shadow-1">
      <q-card-section>
        <div class="text-h6">Session has expired</div>
      </q-card-section>
      <q-card-section>Please login again to proceed.</q-card-section>
      <q-card-section>
        <login-form @submit="submit"></login-form>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import LoginForm from 'components/authentication/LoginForm';

export default {
  methods: {
    submit(loginData) {
      this.$emit('ok', { loginData, hide: this.hide });
    },
    // following method is REQUIRED
    // (don't change its name --> "show")
    show() {
      this.$refs.dialog.show();
    },

    // following method is REQUIRED
    // (don't change its name --> "hide")
    hide() {
      this.$refs.dialog.hide();
    },
  },
  components: {
    LoginForm,
  },
};
</script>
