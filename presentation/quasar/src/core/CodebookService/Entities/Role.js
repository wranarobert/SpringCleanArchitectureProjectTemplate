// API: api/codebook/role/{getAll | add | edit | deleteById}
// Entity -> 'role' from api
export default class {
  constructor(roleData = {}) {
    this.id = roleData.id || null;
    this.name = roleData.name || '';
  }

  static entity() {
    return 'role';
  }
}

export const RoleConstraints = {
  name: {
    presence: true,
  },
};
