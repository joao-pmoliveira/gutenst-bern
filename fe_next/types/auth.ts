type SessionPayload = {
    id: string,
    username: string,
    expiresAt: Date,
    roles: string[],
}

export type { SessionPayload }
