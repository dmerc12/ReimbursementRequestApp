import { getServerSession } from 'next-auth'
import Link from 'next/link'

interface NavbarProps {
  
}

const Navbar = async ({}) => {
    const session = await getServerSession()

    return (
        <div className='fixed backdrop-blur-sm bg-white/75 dark:bg-slate-900 z-50 top-0 left-0 right 0 h-20 border-b border-slate-300 dark:border-slate-700 shadow-sm flex items-center justify-between'>
            <div className='container max-w-7xl mx-auto w-full flex justify-between items-center'>
                <Link href='/home' className={buttonVariants({variants: 'link'})}>Home</Link>
                <Link href='/manage-requests' className={buttonVariants({variants: 'link'})}>Manage Requests</Link>
                <Link href='/manage-employee-information' className={buttonVariants({variants: 'link'})}>Manage Information</Link>
                <div className='md:hidden'>
                    <ThemeToggle></ThemeToggle>
                </div>
                <div className='hidden md:flex gap-4'>
                    <ThemeToggle></ThemeToggle>
                </div>
                {session ? (
                    <>
                        <SignOutButton></SignOutButton>
                    </>
                ) : <SignInButton></SignInButton><RegisterButton></RegisterButton>"}
            </div>
        </div>
    )
}

export default Navbar