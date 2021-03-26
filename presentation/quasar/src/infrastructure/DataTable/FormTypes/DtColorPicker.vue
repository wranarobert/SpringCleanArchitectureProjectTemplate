<template>
  <q-input dense filled :value="value[field]" @input="update" v-bind="qAttrs">
    <template v-slot:append>
      <q-icon name="colorize" class="cursor-pointer">
        <q-popup-proxy transition-show="scale" transition-hide="scale">
          <q-color :value="value[field]" @input="update" />
        </q-popup-proxy>
      </q-icon>
    </template>
  </q-input>
</template>

<script>
import {
  QInput, QIcon, QPopupProxy, QColor,
} from 'quasar';

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
    QInput,
    QIcon,
    QPopupProxy,
    QColor,
  },
};
</script>

<style></style>
