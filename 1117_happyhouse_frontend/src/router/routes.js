import DashboardLayout from "@/views/Layout/DashboardLayout.vue";
import AuthLayout from "@/views/Pages/AuthLayout.vue";

import HouseDeal from "@/views/HouseDeal.vue";
import NotFound from "@/views/NotFoundPage.vue";

// import Member from "@/views/Member.vue";
// import MemberLogin from "@/components/user/MemberLogin.vue";
// import MemberJoin from "@/components/user/MemberJoin.vue";
// import MemberMyPage from "@/components/user/MemberMyPage.vue";

import store from "@/store/index.js";
import router from "./router";
// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다.");
    // next({ name: "SignIn" });
    router.push({ name: "SignIn" });
  } else {
    console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "/",
    redirect: "dashboard",
    component: DashboardLayout,
    children: [
      {
        path: "/dashboard",
        name: "dashboard",
        redirect: "/boardcard",
        component: () => import("../views/Dashboard.vue"),
        children: [
          {
            path: "/card",
            name: "card",
            component: () => import("@/views/HomeCard/Card.vue"),
          },
          {
            path: "/boardcard",
            name: "boardcard",
            component: () => import("@/views/HomeCard/BoardCard.vue"),
          },
          {
            path: "/wishcard",
            name: "wishcard",
            component: () => import("@/views/HomeCard/WishCard.vue"),
          },
          {
            path: "/moneycard",
            name: "moneycard",
            component: () => import("@/views/HomeCard/MoneyCard.vue"),
          },
        ],
      },
      {
        path: "/profile",
        name: "profile",
        beforeEnter: onlyAuthUser,
        component: () => import("../views/Pages/UserProfile.vue"),
      },
      {
        path: "/maps",
        name: "maps",
        component: HouseDeal,
      },
      {
        path: "/mapscopy",
        name: "mapscopy",
        component: () => import("../views/HouseDealCopy.vue"),
      },
      {
        path: "/tables",
        name: "tables",
        component: () => import("../views/RegularTables.vue"),
      },
      {
        path: "/board",
        name: "boards",
        component: () => import("../views/FreeBoard.vue"),
        redirect: "/board/list",
        children: [
          {
            path: "list",
            name: "boardList",
            component: () => import("../views/Boards/BoardList.vue"),
          },
          {
            path: "detail/:articleno",
            name: "boardView",
            component: () => import("../views/Boards/BoardView.vue"),
          },
          {
            path: "write",
            name: "boardWrite",
            beforeEnter: onlyAuthUser,
            component: () => import("../views/Boards/BoardWrite.vue"),
          },
          {
            path: "update/:articleno",
            name: "boardUpdate",
            beforeEnter: onlyAuthUser,
            component: () => import("../views/Boards/BoardUpdate.vue"),
          },
          {
            path: "delete/:articleno",
            name: "boardDelete",
            beforeEnter: onlyAuthUser,
            component: () => import("../views/FreeBoard.vue"),
          },
        ],
      },
      {
        path: "/notice",
        name: "notices",
        component: () => import("../views/NoticeBoard.vue"),
        redirect: "/notice/list",
        children: [
          {
            path: "list",
            name: "noticeList",
            component: () => import("../views/Notices/NoticeList.vue"),
          },
          {
            path: "detail/:articleno",
            name: "noticeView",
            component: () => import("../views/Notices/NoticeView.vue"),
          },
          {
            path: "write",
            name: "noticeWrite",
            beforeEnter: onlyAuthUser,
            component: () => import("../views/Notices/NoticeWrite.vue"),
          },
          {
            path: "update/:articleno",
            name: "noticeUpdate",
            beforeEnter: onlyAuthUser,
            component: () => import("../views/Notices/NoticeUpdate.vue"),
          },
          {
            path: "delete/:articleno",
            name: "noticeDelete",
            beforeEnter: onlyAuthUser,
            component: () => import("../views/NoticeBoard.vue"),
          },
        ],
      },
      {
        path: "/manage",
        name: "manageuser",
        component: () => import("../views/ManageUser.vue"),
        redirect: "/manage/list",
        children: [
          {
            path: "list",
            name: "UserList",
            component: () => import("../views/Manage/UserList.vue"),
          },
        ],
      },
    ],
  },
  {
    path: "/user",
    name: "Member",
    component: AuthLayout,
    children: [
      {
        path: "signin",
        name: "SignIn",
        component: () => import("@/views/Pages/Login.vue"),
        // component: MemberLogin,
      },
      {
        path: "signup",
        name: "SignUp",
        component: () => import("@/views/Pages/Register.vue"),
        // component: MemberJoin,
      },
      {
        path: "mypage",
        name: "MyPage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/views/Pages/UserProfile.vue"),
        // component: MemberMyPage,
      },
    ],
  },
  {
    path: "/",
    redirect: "login",
    component: AuthLayout,
    children: [
      {
        path: "/login",
        name: "login",
        component: () => import("../views/Pages/Login.vue"),
      },
      {
        path: "/register",
        name: "register",
        component: () => import("../views/Pages/Register.vue"),
      },
      { path: "*", component: NotFound },
    ],
  },
];

export default routes;
