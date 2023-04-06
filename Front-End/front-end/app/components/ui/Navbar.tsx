import { getServerSession } from 'next-auth'
import Link from 'next/link'
import SignInButton from '@/app/components/SignInButton'
import SignOutButton from '@/app/components/SignOutButton'
import { buttonVariants } from '@/app/components/ui/Button'

const Navbar = async ({}) => {
    const session = await getServerSession()

    return (
        <nav className='fixed backdrop-blur-sm bg-white/75 dark:bg-slate-900 z-50 top-0 left-0 right-0 bottom-0 h-20 border-b border-slate-300 dark:border-slate-700 shadow-sm flex items-center justify-between'>
            <div className='container max-w-7xl mx-auto w-full flex justify-left items-center'>
                <Link href='/' className={buttonVariants({variant: 'link'})}>Home</Link>
                <Link href='/manage-requests' className={buttonVariants({variant: 'link'})}>Manage Requests</Link>
                <Link href='/manage-employee-information' className={buttonVariants({variant: 'link'})}>Manage Information</Link>
            </div>
            <div className='container max-w-7xl mx-auto w-full flex justify-end items-center'>
                {/* <div className='md:hidden'>
                    <ThemeToggle></ThemeToggle>
                </div>
                <div className='hidden md:flex gap-4'>
                    <ThemeToggle></ThemeToggle>
                </div> */}
                {session ? (
                    <>
                        <SignOutButton></SignOutButton>
                    </>
                ) : <>
                        <SignInButton>LogIn</SignInButton>
                        <Link href='/register' className={buttonVariants({variant: 'link'})}>Register</Link>
                    </>
                } 
            </div>      
        </nav>
    )
}

export default Navbar