<template>
  <q-form ref="loginForm" @submit="submit()">
    <q-input
      square
      filled
      clearable
      v-model="loginData.username"
      label="Username"
      lazy-rules
      :rules="[usernameValidation]"
    />

    <q-input
      square
      filled
      clearable
      type="password"
      v-model="loginData.password"
      label="Password"
      lazy-rules
      :rules="[passwordValidation]"
    />
    <div>
      <q-btn
        class="full-width"
        label="Login"
        color="primary"
        size="lg"
        type="submit"
        :loading="loading"
        :disable="loading"
      />
    </div>
  </q-form>
</template>

<script>
import LoginValidatorMixin from 'src/core/Authentication/LoginValidatorMixin';
import LoginModel from 'src/core/Authentication/LoginModel';

export default {
  props: {
    value: {
      type: LoginModel,
      default: () => new LoginModel({ username: 'user', password: 'enable' }),
    },
    loading: Boolean,
  },
  data() {
    return {
      loginData: new LoginModel(this.value),
    };
  },
  methods: {
    submit() {
      this.$refs.loginForm.validate().then(() => {
        this.$emit('submit', new LoginModel(this.loginData));
      });
    },
  },
  mixins: [LoginValidatorMixin],
};
</script>

<style></style>
