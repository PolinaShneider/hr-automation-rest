import Vue from 'vue';
import Router from 'vue-router';
// Common
import Home from './views/Home.vue';
import Login from './views/user/Login.vue';
import Register from './views/user/Register.vue';
import Positions from './views/hr/Positions.vue';
// Candidate
import OpenPositions from "./views/candidate/OpenPositions";
import MyApplications from "./views/candidate/MyApplications";
import MyInterviews from "./views/candidate/MyInterviews";
// Interviewer
import UpcomingInterviews from "./views/interviewer/UpcomingInterviews";

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('./views/user/Profile.vue')
    },
    {
      path: '/home',
      component: Home
    },
    // Candidate routes
    {
      path: '/open-positions',
      component: OpenPositions
    },
    {
      path: '/my-applications',
      component: MyApplications
    },
    {
      path: '/my-interviews',
      component: MyInterviews
    },
    {
      path: '/positions',
      component: Positions
    },
    // Interviewer routes
    {
      path: '/upcoming-interviews',
      component: UpcomingInterviews
    },
  ]
});

// router.beforeEach((to, from, next) => {
//   const publicPages = ['/login', '/register', '/home'];
//   const authRequired = !publicPages.includes(to.path);
//   const loggedIn = localStorage.getItem('user');

//   // trying to access a restricted page + not logged in
//   // redirect to login page
//   if (authRequired && !loggedIn) {
//     next('/login');
//   } else {
//     next();
//   }
// });
