export default function (userPermissions, permissions, all) {
  if (!permissions) throw new Error('isPermitted called without required arguments');

  if (!userPermissions) return false;

  permissions = Array.isArray(permissions) ? permissions : permissions.trim().split(',');

  const intersection = permissions.reduce((intersect, permission) => {
    if (!userPermissions.map((s) => s.trim()).includes(permission.trim())) {
      return intersect;
    }
    if (!intersect.includes(permission.trim())) {
      intersect.push(permission);
    }
    return intersect;
  }, []);

  return all ? intersection.length >= permissions.length : intersection.length > 0;
}
