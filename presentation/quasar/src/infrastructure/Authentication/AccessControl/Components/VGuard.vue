<script>
export default {
  props: {
    permissions: {
      type: [Array, String],
      default: () => [],
    },
    all: {
      type: Boolean,
      default: false,
    },
  },
  render(h) {
    if (!this.$getPermissions()) {
      console.error('[v-guard]', 'To use v-guard user must be authenticated');
    }

    if (this.all && this.$permitAll(this.permissions)) {
      return this.$slots.default;
    }

    if (!this.all && this.$permitAny(this.permissions)) {
      return this.$slots.default;
    }

    return h();
  },
};
</script>
