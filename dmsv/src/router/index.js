import { createRouter, createWebHistory } from "vue-router"

const routes = [
    {
        path: '/',
        name: 'login',
        hidden: true,
        component: () => import('@/views/login/MainLogin')
    },
    {
        path: '/student',
        name: 'student',
        meta:{name:'学生管理',}, 
        redirect: '/student/self',
        component: () => import('@/components/Student/StudentMain'),
        children: [{
            path: '/student/self',
            name: 'studentself',
            meta:{name:'个人',},
            redirect: '/student/self/show',
            icon:'fa fa-user', 
            children: [
                {
                    path: '/student/self/show',
                    name: 'studentselfshow',
                    meta:{name:'个人信息',}, 
                    icon:'fa fa-address-card-o',
                    component: () => import('@/components/Student/self/ShowSelf'),
                },
                {
                    path: '/student/self/alter',
                    name: 'studentselfalter',
                    meta:{name:'修改个人信息',}, 
                    icon:'fa fa-pencil',
                    component: () => import('@/components/Student/self/AlterSelf'),
                }
            ],
        },
        {
            path: '/student/dorm',
            name: 'studentdorm',
            meta:{name:'宿舍',}, 
            redirect: '/student/dorm/show',
            icon:'fa fa-bath',
            children: [{
                path: '/student/dorm/show',
                name: 'studentdormshow',
                meta:{name:'宿舍信息',}, 
                icon:'fa fa-address-card-o',
                component: () => import('@/components/Student/dorm/ShowDorm'),
            },
            {
                path: '/student/dorm/wealth',
                meta:{name:'宿舍财产',}, 
                name: 'studentdormwealth',
                icon:'fa fa-pencil',
                component: () => import('@/components/Student/dorm/WealthDorm'),
            }],
        },
        {
            path: '/student/visit',
            name: 'studentvisit',
            meta:{name:'访客',},
            redirect: '/student/visit/add', 
            icon:'fa fa-user-o', 
            children: [{
                path: '/student/visit/add',
                name: 'studentvisitadd',
                meta:{name:'新访客',}, 
                icon:'fa fa-user-o', 
                component: () => import('@/components/Student/visitor/AddVisitor'),
            },
            {
                path: '/student/visit/show',
                name: 'studentvisitshow',
                meta:{name:'来访记录',}, 
                icon:'fa fa-pencil',
                component: () => import('@/components/Student/visitor/ShowVisitor'),
            },
            {
                path: '/student/visit/alter',
                name: 'studentvisitalter',
                meta:{name:'修改记录',}, 
                hidden: true,
                icon:'fa fa-pencil',
                component: () => import('@/components/Student/visitor/AlterVisitor'),
            }
            ],
        },
        {
            path: '/student/floor',
            name: 'studentfloor',
            meta:{name:'宿舍楼',}, 
            redirect: '/student/floor/show', 
            icon:'fa fa-university',
            children: [{
                path: '/student/floor/show',
                name: 'studentfloorshow',
                meta:{name:'宿舍楼信息',}, 
                icon:'fa fa-university',
                component: () => import('@/components/Student/floor/ShowFloor'),
            }],
        },
        {
            path: '/student/account',
            name: 'studentaccount',
            meta:{name:'账号',}, 
            redirect: '/student/account/alter', 
            icon:'fa fa-vcard',
            children: [{
                path: '/student/account/alter',
                name: 'studentaccountalter',
                meta:{name:'修改账号',}, 
                icon:'fa fa-vcard',
                component: () => import('@/components/Student/account/AlterAccount'),
            },
            {
                path: '/student/account/logout',
                name: 'studentaccountlogout',
                meta:{name:'退出',}, 
                icon:'fa fa-vcard',
                component: () => import('@/components/Student/account/LogoutAccount'),
            }],
        },
        ]
    },
    {
        path: '/maintainer',
        name: 'maintainer',
        meta:{name:'水电工',}, 
        redirect: '/maintainer/self',
        component: () => import('@/components/Maintainer/MaintainerMain'),
        children: [{
            path: '/maintainer/self',
            name: 'maintainerself',
            meta:{name:'个人',}, 
            redirect: '/maintainer/self/show',
            icon:'fa fa-user',
            children: [
                {
                    path: '/maintainer/self/show',
                    name: 'maintainerselfshow',
                    meta:{name:'个人信息',}, 
                    icon:'fa fa-user',
                    component: () => import('@/components/Maintainer/self/ShowSelf'),
                },
                {
                    path: '/maintainer/self/alter',
                    name: 'maintainerselfalter',
                    meta:{name:'修改个人信息',}, 
                    icon:'fa fa-pencil',
                    component: () => import('@/components/Maintainer/self/AlterSelf'),
                }
            ],
        },
        {
            path: '/maintainer/repairlist',
            name:'maintainerrepairlist',
            meta:{name:'报修',}, 
            redirect: '/maintainer/repairlist/show',
            icon:'fa fa-vcard',
            children: [{
                path: '/maintainer/repairlist/show',
                name:'maintainerrepairlistshow',
                meta:{name:'报修信息',}, 
                icon:'fa fa-pencil',
                component: () => import('@/components/Maintainer/repairlist/ShowRepairlist'),
            }],
        },
        {
            path: '/maintainer/maintained',
            name:'maintainermaintained',
            meta:{name:'已修',}, 
            redirect: '/maintainer/maintained/add',
            icon:'fa fa-pencil',
            children: [{
                path: '/maintainer/maintained/add',
                name:'maintainermaintainedadd',
                meta:{name:'完成修理',}, 
                icon:'fa fa-pencil',
                component: () => import('@/components/Maintainer/maintained/AddMaintain'),
            },
            {
                path: '/maintainer/maintained/show',
                name:'maintainermaintainedshow',
                meta:{name:'修理信息',}, 
                icon:'fa fa-pencil',
                component: () => import('@/components/Maintainer/maintained/ShowMaintain'),
            },]
        },
        {
            path: '/maintainer/account',
            name:'maintaineraccount',
            meta:{name:'账号',}, 
            redirect: '/maintainer/account/alter',
            icon:'fa fa-vcard',
            children: [{
                path: '/maintainer/account/alter',
                name:'maintaineraccountalter',
                meta:{name:'修改账号',}, 
                icon:'fa fa-pencil',
                component: () => import('@/components/Maintainer/account/AlterAccount'),
            },
            {
                path: '/maintainer/account/logout',
                name: 'maintaineraccountlogout',
                meta:{name:'退出',}, 
                icon:'fa fa-vcard',
                component: () => import('@/components/Maintainer/account/LogoutAccount'),
            }],
        },
        ]
    },
    {
        path: '/administrator',
        name: 'administrator',
        meta:{name:'舍管',}, 
        redirect: '/administrator/self',
        component: () => import('@/components/Administrator/AdministratorMain'),
        children: [{
            path: '/administrator/self',
            name: 'administratorself',
            icon:'fa fa-user',
            redirect: '/administrator/self/show',
            meta:{name:'个人',}, 
            children: [
                {
                    path: '/administrator/self/show',
                    name: 'administratorselfshow',
                    meta:{name:'个人信息',}, 
                    icon:'fa fa-vcard',
                    component: () => import('@/components/Administrator/self/ShowSelf'),
                },
                {
                    path: '/administrator/self/alter',
                    name: 'administratorselfalter',
                    meta:{name:'修改个人信息',}, 
                    icon:'fa fa-pencil',
                    component: () => import('@/components/Administrator/self/AlterSelf'),
                }
            ],
        },
        {
            path: '/administrator/stu',
            name: 'administratorstu',
            meta:{name:'学生管理',}, 
            icon:'fa fa-users',
            redirect: '/administrator/stu/show',
            children: [{
                path: '/administrator/stu/show',
                name: 'administratorstushow',
                meta:{name:'查看学生',}, 
                icon:'fa fa-users',
                component: () => import('@/components/Administrator/student/ShowStu'),
            },
            {
                path: '/administrator/stu/alter',
                name: 'administratorstualter',
                meta:{name:'修改学生',},
                icon:'fa fa-pencil', 
                hidden: true,
                component: () => import('@/components/Administrator/student/AlterStu'),
            },
            ],
        },
        {
            path: '/administrator/dorm',
            name: 'administratordorm',
            meta:{name:'宿舍管理',}, 
            icon:'fa fa-university',
            redirect: '/administrator/dorm/show',
            children: [{
                path: '/administrator/dorm/show',
                name: 'administratordormshow',
                meta:{name:'宿舍信息',}, 
                icon:'fa fa-pencil', 
                component: () => import('@/components/Administrator/dorm/ShowDorm'),
            },
            {
                path: '/administrator/dorm/wealth',
                meta:{name:'宿舍财产',}, 
                name: 'administratordormwealth',
                icon:'fa fa-pencil', 
                component: () => import('@/components/Administrator/dorm/WealthDorm'),
            },
            {
                path: '/administrator/dorm/reapir',
                meta:{name:'查询报修',}, 
                icon:'fa fa-pencil', 
                name: 'administratorshowrepair',
                component: () => import('@/components/Administrator/dorm/ShowRepair'),
            },
            {
                path: '/administrator/dorm/alter',
                meta:{name:'修改报修',}, 
                icon:'fa fa-pencil', 
                name: 'administratordormalter',
                component: () => import('@/components/Administrator/dorm/AlterRepair'),
            },
        ],
        },
        {
            path: '/administrator/floor',
            name: 'administratorfloor',
            meta:{name:'宿舍楼管理',}, 
            icon:'fa fa-pencil', 
            redirect: '/administrator/floor/show',
            children: [{
                path: '/administrator/floor/show',
                name: 'administratorfloorshow',
                meta:{name:'宿舍楼信息',}, 
                icon:'fa fa-pencil', 
                component: () => import('@/components/Administrator/floor/ShowFloor'),
            },
            {
                path: '/administrator/floor/alter',
                name: 'administratorflooralter',
                meta:{name:'修改楼信息',}, 
                icon:'fa fa-pencil', 
                component: () => import('@/components/Administrator/floor/AlterFloor'),
            }
        ],
        },
        {
            path: '/administrator/account',
            name: 'administratoraccount',
            meta:{name:'账号',}, 
            icon:'fa fa-vcard', 
            redirect: '/administrator/account/alter',
            children: [{
                path: '/administrator/account/alter',
                name: 'administratoraccountalter',
                meta:{name:'修改账号',}, 
                icon:'fa fa-pencil', 
                component: () => import('@/components/Administrator/account/AlterAccount'),
            },
            {
                path: '/administrator/account/logout',
                name: 'administratoraccountlogout',
                meta:{name:'退出',}, 
                icon:'fa fa-vcard',
                component: () => import('@/components/Administrator/account/LogoutAccount'),
            }],
        },
        ]
    },
    {
        path: "/404",
        name: "404Error",
        hidden: true,
        component: () => import("@/views/error/404Error")
    },
]
export const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

export default router
