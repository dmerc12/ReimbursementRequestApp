import NextAuth from "next-auth"
import CredentialsProvider from "next-auth/providers/credentials";

export const authOptions = {
  // Configure one or more authentication providers
  providers: [
    CredentialsProvider({
        // The name to display on the sign in form (e.g. "Sign in with...")
        name: "Credentials",
        // `credentials` is used to generate a form on the sign in page.
        // You can specify which fields should be submitted, by adding keys to the `credentials` object.
        // e.g. domain, username, password, 2FA token, etc.
        // You can pass any HTML attribute to the <input> tag through the object.
        credentials: {
          email: { label: "Email", type: "email" },
          password: { label: "Password", type: "password" }
        },
        async authorize(credentials) {
            try {
            // Add logic here to look up the user from the credentials supplied
            const response = await fetch('http://localhost:8080/login/now', {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    'email': credentials.email,
                    'password' : credentials.password
                })
              })
        
              const userData = await response.json()
              const user = {
                id: userData.employeeId,
                name: `${userData.firstName} ${userData.lastName}`,
                email: userData.email
                // Add any other user properties you need to store in the session here
              }
              return user
            } catch (error) {
                if (error instanceof Error) {
                  throw new Error('Invalid email or password')
                } else {
                  // Handle the error message returned by the Java backend
                  const errorMessage = await error.json()
                  throw new Error(errorMessage.message)
                }
              }
            }})]}

export default NextAuth(authOptions)