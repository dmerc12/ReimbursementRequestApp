'use client'

import { FC, useState } from 'react'
import Button from '@/app/components/ui/Button'
import { signIn } from 'next-auth/react'
import { toast } from '@/app/components/ui/Toast'

interface SignInButtonProps {
  
}

const SignInButton: FC<SignInButtonProps> = ({}) => {
    const [isLoading, setIsLoading] = useState<boolean>(false)

    const loginWithCredentials = async () => {
        setIsLoading(true)

        try {
            await signIn('credentials')
        } catch (error) {
            toast({
                title: 'Error signing in',
                message: 'Please try again later',
                type: 'error'
            })
        }
    }

    return (
        <Button onClick={loginWithCredentials} isLoading={isLoading}>
            Login
        </Button>
    )
}

export default SignInButton