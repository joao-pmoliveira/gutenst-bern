import { z } from "zod";

export const SignupFormSchema = z.object({
    username: z.string()
        .min(2, { message: "Name must be at least 2 characters long." })
        .trim(),
    password: z.string()
        .min(4, { message: "Passowrd must be at least 4 characters long." })
        .trim(),
})

export type FormState = | {
    errors?: { username?: string[], password?: string[] },
    message?: string
} | undefined;


export const SignupResponseSchema = z.object({
    id: z.number(),
    username: z.string()
        .min(2)
        .trim(),
    roles: z.array(
        z.string()
    )
})
