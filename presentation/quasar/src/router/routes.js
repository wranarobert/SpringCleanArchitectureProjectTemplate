import LoginLayout from 'layouts/LoginLayout';
import UserLayout from 'layouts/UserLayout';

import AdminGuard from './guards/AdminGuard';
import NotAuthenticatedGuard from './guards/NotAuthenticatedGuard';

const routes = [
  {
    path: '/',
    component: UserLayout,
    children: [
      {
        path: 'roles',
        component: () => import('pages/Roles.vue'),
        meta: {
          guard: {
            permissions: ['IDENTITY_MODULE'],
            all: true,
          },
        },
      },
      {
        path: 'users',
        component: () => import('pages/Users.vue'),
        meta: {
          guard: {
            permissions: ['IDENTITY_MODULE'],
            all: true,
          },
        },
      },
      {
        path: 'upload/test',
        component: () => import('pages/Upload.vue'),
      },
      {
        path: 'editor',
        component: () => import('pages/EditorPage.vue'),
      },
    ],
    beforeEnter: AdminGuard,
  },
  {
    path: '/login',
    component: LoginLayout,
    beforeEnter: NotAuthenticatedGuard,
    children: [
      {
        path: '',
        component: () => import('pages/Login.vue'),
      },
    ],
  },
  {
    path: '/403',
    component: () => import('pages/Error404.vue'),
  },
  {
    path: '*',
    component: () => import('pages/Error404.vue'),
  },
];

export default routes;
