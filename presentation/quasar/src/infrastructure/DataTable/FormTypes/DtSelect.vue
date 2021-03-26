<template>
  <q-select
    dense
    :value="value[field]"
    @input="update"
    :options="list"
    :option-value="optionValue"
    :option-label="optionLabel"
    :loading="loading"
    map-options
    emit-value
    v-bind="qAttrs"
  />
</template>

<script>
import { QSelect } from 'quasar';

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
    options: {
      type: Function,
      default: () => Promise.resolve([]),
    },
    optionValue: {
      type: String,
      default: 'id',
    },
    optionLabel: {
      type: String,
      default: 'label',
    },
    qAttrs: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      loading: false,
      list: [],
    };
  },
  computed: {
    attrs() {
      const { ...attrs } = this.$attrs;
      return attrs;
    },
  },
  methods: {
    update(value) {
      this.$emit('input', { ...this.value, [this.field]: value });
    },
  },
  watch: {
    options: {
      immediate: true,
      handler(current) {
        this.loading = true;

        current()
          .then(list => {
            this.list = list;
          })
          .finally(() => {
            this.loading = false;
          });
      },
    },
  },
  components: {
    QSelect,
  },
};
</script>

<style></style>
