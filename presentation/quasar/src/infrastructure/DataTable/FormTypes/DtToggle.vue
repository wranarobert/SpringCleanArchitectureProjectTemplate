<template>
  <q-toggle dense :value="value[field]" @input="update($event)" v-bind="qAttrs" />
</template>

<script>
import { QToggle } from 'quasar';

export default {
  inheritAttrs: false,
  props: {
    value: {
      type: Object,
      required: true,
    },
    field: {
      type: String,
      required: true,
    },
    qAttrs: {
      type: Object,
    },
  },
  methods: {
    update(value) {
      this.$emit('input', { ...this.value, [this.field]: value });
    },
  },
  components: {
    QToggle,
  },
};
</script>

<style>
</style>
