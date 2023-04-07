'use client'

import { FC, useState } from 'react'
import Button from '@/app/components/ui/Button'
import { signOut } from 'next-auth/react'
import { toast } from '@/app/components/ui/Toast'

interface SignOutButtonProps {
  
}

const SignOutButton: FC<SignOutButtonProps> = ({}) => {
    const [isLoading, setIsLoading] = useState<boolean>(false)

    const signUserOut = async () => {
        setIsLoading(true)

        try {
            await signOut()
        } catch (error) {
            toast({
                title: 'Error signing out',
                message: 'Please try again later',
                type: 'error'
            })
        }
    }

    return (
        <Button onClick={signUserOut} isLoading={isLoading}>
            Logout
        </Button>
    )
}

export default SignOutButton